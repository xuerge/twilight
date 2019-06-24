package com.xuerge.twilight;

import com.google.common.reflect.Reflection;
import com.xuerge.twilight.impl.MethodInvokeAction;
import com.xuerge.twilight.util.ReflectionUtil;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.Queue;

public class ActionExecutor<S, E, C> {
    private Queue<Action> queue = new LinkedList<Action>();
    private StateMachine machine;
    private Class[] parameterTypes;

    public ActionExecutor(StateMachine<S, E, C> stateMachine) {
        this.machine = stateMachine;
        parameterTypes = new Class[]{machine.getState(), machine.getState(),
                machine.getEvent(), machine.getContext()};
    }


    public void execute(Action action, S from, S to, E event, C context) {
        leaveState(from);
        entryState(to);
        doExecute(from,to,event,context);
        action.execute(machine, from, to, event, context);
    }

    private void doExecute(S from, S to, E event, C context) {
        while (queue.size()>0){
            Action action = queue.poll();
            action.execute(machine,from, to, event, context);
        }
    }

    private void entryState(S to) {
        String methodName = "entry" + to;
        Method method = ReflectionUtil.getMethod(machine.getImplClass(), methodName, parameterTypes);
        if (null != method) {
            Action action = new MethodInvokeAction(machine.getImplClass(), methodName, parameterTypes);
            queue.add(action);
        }
    }

    private void leaveState(S from) {
        String methodName = "leave" + from;
        Method method = ReflectionUtil.getMethod(machine.getImplClass(), methodName, parameterTypes);
        if (null != method) {
            Action action = new MethodInvokeAction(machine.getImplClass(), methodName, parameterTypes);
            queue.add(action);
        }
    }
}
