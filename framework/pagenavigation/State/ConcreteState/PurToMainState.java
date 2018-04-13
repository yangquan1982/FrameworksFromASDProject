/**
 * 
 */
package framework.pagenavigation.State.ConcreteState;

import framework.pagenavigation.Mediator.AbstractMediator.APageNavigator;
import framework.pagenavigation.State.AbstractState.INavigatorState;

/**
 * @author tbg12
 *
 */
public class PurToMainState extends INavigatorState {
	
	public PurToMainState(APageNavigator navigator) {
		super(ENavState.FROMBTOA, navigator);
	}

	@Override
	public void navigate() {
		navigator.setCurrentState(navigator.getFromAToBState());
	}

}
