/**
 * Mediator Interface for UI framework
 */
package framework.pagenavigation.Mediator.AbstractMediator;

import framework.pagenavigation.State.AbstractState.INavigatorState;
import shopping.ui.abstractproduct.APage;

/**
 * @author Quan Yang
 *
 */
public abstract class APageNavigator {
	protected APage[] pages;
	protected INavigatorState fromAToBState;
	protected INavigatorState fromBToAState;
	protected INavigatorState currentState;
	public APageNavigator() {
		this.pages = new APage[2];
	}

	public INavigatorState getCurrentState() {
		return this.currentState;
	}
	public void setCurrentState(INavigatorState currentState) {
		this.currentState = currentState;
	}

	public INavigatorState getFromAToBState() {
		return this.fromAToBState;
	}
	public void setFromAToBState(INavigatorState fromAToBState) {
		this.fromAToBState = fromAToBState;
	}
	public INavigatorState getFromBToAState() {
		return this.fromBToAState;
	}
	public void setFromBToAState(INavigatorState fromBToAState) {
		this.fromBToAState = fromBToAState;
	}
//	public void addPage(APage page) {
//		if (this.pages != null) {//one concrete navigator only has two colleagues at most
//			this.pages.add(page);
//		}
//	}
	public void setPageAB(APage pageA, APage pageB) {
		this.pages[0] = pageA;
		this.pages[1] = pageB;
	}
	
	public abstract void navigate(APage senderPage);
}
