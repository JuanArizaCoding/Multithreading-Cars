public class TrafficController {
	
    private int left; //TRUE: left>0    FALSE: left==0
    private int right;//TRUE: right>0    FALSE: right==0
    
    public TrafficController() {
        this.left=0;
        this.right=0;
    }

    public synchronized void enterLeft() {
    	try {
    		while(this.right>0) {
    			wait();
    		}
    		
    		//ENTER A NEW CAR IN THE BRIDGE -> left: TRUE
    		this.left++;
    		System.out.println("ENTER LEFT. Total left: "+this.left);
    	} catch(InterruptedException e) {
    		e.printStackTrace();
    	}

    }

    public synchronized void leaveLeft() {
    	this.right--;
    	if(this.right==0) {
    		//NO CARS ON RIGHT -> right: FALSE
    		notifyAll();
    		System.out.println("LEAVE LEFT. Total right: "+this.right);
    	}
    }

    public synchronized void enterRight() {
    	try {
    		while(this.left>0) {
    			wait();
    		}
    		
    		//ENTER A NEW CAR IN THE BRIDGE -> right: TRUE
    		this.right++;
    		System.out.println("ENTER RIGHT. Total right: "+this.right);
    	} catch(InterruptedException e) {
    		e.printStackTrace();
    	}
    }

    public synchronized void leaveRight() {
    	this.left--;
    	if(this.left==0) {
    		//NO CARS ON LEFT -> left: FALSE
    		notifyAll();
    		System.out.println("LEAVE RIGHT. Total left: "+this.left);
    	}
    }
}

