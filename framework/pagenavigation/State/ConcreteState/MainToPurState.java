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
public class MainToPurState extends INavigatorState {

	
	public MainToPurState(APageNavigator navigator) {
		super(ENavState.FROMATOB, navigator);
	}

	@Override
	public void navigate() {
		navigator.setCurrentState(navigator.getFromBToAState());
	}

}
