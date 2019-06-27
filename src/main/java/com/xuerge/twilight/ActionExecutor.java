package com.xuerge.twilight;

import com.xuerge.twilight.impl.ExternalTransition;
import com.xuerge.twilight.impl.InternalTransition;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ActionExecutor<S, E, C> {
    private Queue<Action> queue = new LinkedList<Action>();
    private StateMachine<S, E, C> machine;
    private Class[] parameterTypes;
    private ExecutorService executorPool = Executors.newFixedThreadPool(1);

    public ActionExecutor(StateMachine<S, E, C> stateMachine) {
        this.machine = stateMachine;
        parameterTypes = new Class[]{machine.getStateClass(), machine.getStateClass(),
                machine.getEventClass(), machine.getContextClass()};
    }


    public void execute(Action action, Object from,Object to,Object context) {
        if (null == action)
            return;
        action.execute(machine, from, to, null,context);
    }

    public void execute(Transition<S, E, C> t, C context) {
        prepareActions(t);
        executeActions(t, context);
    }

    public Future executeSyn(Transition<S, E, C> t, C context) {
        prepareActions(t);
        return executeActionsSyn(t, context);
    }

    private void prepareActions(Transition<S, E, C> t) {
        if (t instanceof InternalTransition) {
            addAction(t.getAction());
        } else if (t instanceof ExternalTransition) {
            addAction(machine.getStates().get(t.getFrom().toString()).getLeaveAction());
            addAction(t.getAction());
            addAction(machine.getStates().get(t.getTo().toString()).getEntryAction());
        }
    }

    private void addAction(Action action) {
        if (null != action) {
            queue.add(action);
        }
    }

    private void onState(Transition<S, E, C> transition) {
        if (null != transition.getAction()) {
            queue.add(transition.getAction());
        }
    }

    private void executeActions(Transition<S, E, C> transition, C context) {
        while (queue.size() > 0) {
            Action action = queue.poll();
            action.execute(machine, transition.getFrom(), transition.getTo(), transition.getEvent(), context);
        }
    }


    private Future executeActionsSyn(Transition<S, E, C> transition, C context) {
        while (queue.size() > 0) {
            Action action = queue.poll();
            action.execute(machine, transition.getFrom(), transition.getTo(), transition.getEvent(), context);
        }

        Future f = executorPool.submit(() -> {

        });

        return null;
    }


}
