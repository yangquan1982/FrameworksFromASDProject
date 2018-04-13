/**
 * 
 */
package framework.pagenavigation.State.ConcreteState;

import framework.pagenavigation.Mediator.AbstractMediator.APageNavigator;
import framework.pagenavigation.State.AbstractState.INavigatorState;

/**
 * @author Quan Yang
 *
 */
public class LogToMainState extends INavigatorState {

	public LogToMainState(APageNavigator navigator) {
		super(ENavState.FROMATOB, navigator);
	}

	@Override
	public void navigate() {
		navigator.setCurrentState(navigator.getFromBToAState());
	}

}
