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
public class BillToPurState extends INavigatorState {

	public BillToPurState(APageNavigator navigator) {
		super(ENavState.FROMBTOA, navigator);
	}

	@Override
	public void navigate() {
		navigator.setCurrentState(navigator.getFromAToBState());
	}

}
