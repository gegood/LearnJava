package com.example.common.heartbeat.data;

import com.example.common.utils.EnumUtil;
import lombok.Getter;

import java.util.Optional;

/**
 * @author wuzhihao
 * @date 2023/08/23
 */

@Getter
public enum AgentStatusEnum {

    /**
     * 已下线
     */
    OFFLINE(1, "OFFLINE", "已下线"),
    /**
     * 已激活
     */
    REGISTERED(2, "REGISTERED", "已激活");

    private final Integer value;
    private final String name;
    private final String description;

    AgentStatusEnum(Integer value, String name, String description) {
        this.value = value;
        this.name = name;
        this.description = description;
    }

    public static AgentStatusEnum getEnumByValue(Integer value){
        Optional<AgentStatusEnum> m1 = EnumUtil.getEnumObject(AgentStatusEnum.class, e -> e.getValue().equals(value));
        return m1.isPresent() ? m1.get() : null;
    }

    public static AgentStatusEnum getEnumByName(String name){
        Optional<AgentStatusEnum> m1 = EnumUtil.getEnumObject(AgentStatusEnum.class, e -> e.getName().equals(name));
        return m1.isPresent() ? m1.get() : null;
    }

}
