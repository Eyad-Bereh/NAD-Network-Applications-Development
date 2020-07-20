
public class ConnectionFlagWatcher implements Runnable {
	private boolean flag;
	private boolean callbackIsset = false;
	private Callback onTrue;
	private Callback onFalse;
	
	public ConnectionFlagWatcher(boolean flag) {
		this.flag = flag;
	}
	
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
	public boolean getFlag() {
		return this.flag;
	}
	
	public void toggleFlag() {
		this.flag = !this.flag;
	}
	
	public void attachCallback(Callback onTrue, Callback onFalse) throws Exception {
		if (!this.callbackIsset) {
			this.onTrue = onTrue;
			this.onFalse = onFalse;
			this.callbackIsset = true;
		}
		else {
			throw new Exception("ConnectionFlagWatcher.attachCallback: callback already attached.");
		}
	}
	
	@Override
	public void run() {
		while (true) {
			if (this.flag) {
				this.onTrue.execute(null);
			}
			else {
				this.onFalse.execute(null);
			}
		}
	}

}
