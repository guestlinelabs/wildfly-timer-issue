# wildfly-timer-issue
A minimal example project which demonstrates the [WFCORE-4967](https://issues.redhat.com/browse/WFCORE-4967) EJB timer issue

## Root cause
RequestController is not thread safe when in paused mode.

- Entry: [ControlPoint.requestComplete()](https://github.com/wildfly/wildfly-core/blob/f620c0f6c8463114971df4489125e87cbe44e322/request-controller/src/main/java/org/wildfly/extension/requestcontroller/ControlPoint.java#L173)
used by ManagedExecutorServiceImpl / ManagedScheduledExecutorImpl
- problematic method: [RequestController.findForcedTask()](https://github.com/wildfly/wildfly-core/blob/f620c0f6c8463114971df4489125e87cbe44e322/request-controller/src/main/java/org/wildfly/extension/requestcontroller/RequestController.java#L399)

## Recreation steps
1. Deploy sample application to the wildfly server (via cli or just by copy it to standalone/deployments).
2. Start the server

#### Note
Above order is very important because when app is deployed after RequestController is resumed, issue is not occurring.

### Expected
All timers from SomeTimers class are triggered after the server is resumed. 

This can be confirmed by finding an entry in server log at INFO level, which message starts with `timers OK`
### Incorrect behaviour
Some (random) timers are omitted, there is a message in logs saying `timers FAIL` or there is no log entry at all. 
