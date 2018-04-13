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
public class PListToMainState extends INavigatorState {

	
	public PListToMainState(APageNavigator navigator) {
		super(ENavState.FROMBTOA, navigator);
	}

	@Override
	public void navigate() {
		navigator.setCurrentState(navigator.getFromAToBState());
	}

}
