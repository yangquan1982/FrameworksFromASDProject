/**
 * 
 */
package framework.pagenavigation.State.AbstractState;

import framework.pagenavigation.Mediator.AbstractMediator.APageNavigator;
import framework.pagenavigation.State.ConcreteState.ENavState;

/**
 * @author Quan Yang
 *
 */
public abstract class INavigatorState {
	protected ENavState state;
	protected APageNavigator navigator;
	public INavigatorState(ENavState state, APageNavigator navigator) {
		this.state = state;
		this.navigator = navigator;
	}
	
	public ENavState getState() {
		return state;
	}

	public APageNavigator getNavigator() {
		return navigator;
	}

	public abstract void navigate();
}
