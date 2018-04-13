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
public class LogToRegState extends INavigatorState {

	
	public LogToRegState(APageNavigator navigator) {
		super(ENavState.FROMBTOA, navigator);
	}

	@Override
	public void navigate() {
		navigator.setCurrentState(navigator.getFromAToBState());
	}

}
