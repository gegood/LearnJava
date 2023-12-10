package com.example.myconsole.heartbeat.service;

import com.example.common.heartbeat.data.Agent;
import com.example.common.service.CrudServiceDaoImpl;
import com.example.myconsole.heartbeat.dao.AgentDao;
import com.example.myconsole.heartbeat.domain.AgentDo;
import com.example.myconsole.heartbeat.po.AgentPo;
import com.example.myconsole.heartbeat.po.AgentPo_;
import com.example.myconsole.heartbeat.service.mapper.AgentMapper;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import sun.management.resources.agent;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class AgentServiceImpl extends CrudServiceDaoImpl<Long, AgentDo, AgentSearchCondition, AgentPo, AgentDao, AgentMapper>
        implements AgentService{

    @Override
    protected Specification<AgentPo> toSpec(AgentSearchCondition condition) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            // TODO 下列判断条件为自动生成，请按需修改 ，此处祥总使用 groovy 脚本生成，还没看懂，手写也可以
            if (condition.getId() != null) {
                Predicate p = cb.equal(root.get(AgentPo_.ID), condition.getId());
                predicates.add(p);
            }
            if (condition.getAgentId() != null) {
                Predicate p = cb.equal(root.get(AgentPo_.agentId), condition.getAgentId());
                predicates.add(p);
            }
            if (StringUtils.isNotBlank(condition.getName())) {
                Predicate p = cb.equal(root.get(AgentPo_.name), condition.getName());
                predicates.add(p);
            }
            query.where(cb.and(predicates.toArray(new Predicate[0])));
            //log.info("HacddtsHaStrategySearch toSpec:{}", JSONObject.toJSONString(condition));
            /* 此处实现排序 */
//            if (condition.getOrderBy() != null) {
//                if (condition.getAscend() != null && condition.getAscend()) {
//                    query.orderBy(cb.asc(root.get(condition.getOrderBy())));
//                } else {
//                    query.orderBy(cb.desc(root.get(condition.getOrderBy())));
//                }
//            }
            Predicate result = query.getRestriction();
            return  result;
        };
    }

    @Override
    public void handleHeartbeat(Agent agent) {
        log.info("handleHeartbeat.agent = " + agent);
        AgentDo agentDo = new AgentDo();
        //agentDo.setId(System.currentTimeMillis());
        agentDo.setAgentId(agent.getAgentId());
        agentDo.setName(agent.getName());
        agentDo.setPort(agent.getPort());
        agentDo.setStatus(agent.getStatus());
        agentDo.setReportTime(agent.getReportTime());
        agentDo.setCreatedDate(System.currentTimeMillis());

        save(agentDo);
    }

    @Override
    public void save(AgentDo agentDo) {

//        AgentPo byAgentId = dao().findAgentPoByAgentId(agentDo.getAgentId());
//        if(byAgentId == null){
//            byAgentId = new AgentPo();
//        }
//
//        byAgentId.setId(System.currentTimeMillis();
//        byAgentId.setAgentId(agentDo.getAgentId());
//        byAgentId.setName(agentDo.getName());
//        byAgentId.setPort(agentDo.getPort());
//        byAgentId.setStatus(agentDo.getStatus());
//        byAgentId.setReportTime(agentDo.getReportTime());
//        byAgentId.setCreatedDate(System.currentTimeMillis());
//
//        agentDao.save(byAgentId);

        AgentPo byAgentId = dao().findAgentPoByAgentId(agentDo.getAgentId());
        if(byAgentId == null){
            create(agentDo);
        }else{
            update(byAgentId.getId(), agentDo);
        }

        AgentSearchCondition condition = new AgentSearchCondition();
        condition.setAgentId(agentDo.getAgentId());
        List<AgentDo> list = list(condition);
        log.info(list.toString());

    }
}
