package com.xuerge.twilight.annotation;

import com.xuerge.twilight.impl.BaseStateMachine;

@StateMachineDefinition(stateType = RlsState.class, eventType = RlsEvent.class, contextType = RlsContext.class)
@Transitions({
        @Transition(from = "A", to = "B", on = "AtoB", callMethod = "fromAtoB"),
        @Transition(from = "B", to = "C", on = "BtoC", callMethod = "fromBtoC"),
        @Transition(from = "C", to = "D", on = "CtoD", callMethod = "fromCtoD")
})
public class RlsStateMachine extends BaseStateMachine {
    public void fromAtoB(RlsState from, RlsState to, RlsEvent event, RlsContext context) {
        System.out.println("execute method fromAtoB");
    }

    public void fromBtoC(RlsState from, RlsState to, RlsEvent event, RlsContext context) {
        System.out.println("execute method fromBtoC");
    }

    public void fromCtoD(RlsState from, RlsState to, RlsEvent event, RlsContext context) {
        System.out.println("execute method fromCtoD");
    }
}
