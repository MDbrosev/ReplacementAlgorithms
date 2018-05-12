import java.util.ArrayDeque;

/**
 *
 * @author Yoseph
 */

public class fifoReplacement extends ReplacementAlgorithm {    
    /*ArrayDeque is implemented to hold the reference strings because 
    a empty array deque can be constructed with an initial capacity 
    sufficient to hold the specified number of elements.
    It also allows for O(1) addition and removal of first and last elements.
    */
    private final ArrayDeque<Integer> referencePage;   
    //frameSize, is an integer that will be used to set the size of referencePage.
    private final int frameSize;    
    
    //Constructor, param is pageFrameCount
    public fifoReplacement(int pageFrameCount) {       
        //Reusing parent class(ReplacementAlgorithm) constructor
	super(pageFrameCount);       
        //Storing the number of physical page frames
	frameSize = pageFrameCount;       
        //Initializing referencePage with the page frame size
	referencePage = new ArrayDeque<>(frameSize);
    }
    
    //Override superclass method insert() to perform FIFO algorithm
    @Override 
    public void insert(int pageNumber) {        
        fifoOperation(pageNumber); 
    }
    
    //Algorithm to perform FIFO
    private void fifoOperation(int pageNumber) {
	//If the page is in use
	if(referencePage.contains(pageNumber))
            return;			        
        //If referencePage is full
	if(referencePage.size() == frameSize) {            
            //Remove the first(oldest) element that was entered into referencePage.
            referencePage.removeLast();
            //Insert new pageNumber at the front of referencePage.
            referencePage.push(pageNumber);
            //Increment page fault count.
            pageFaultCount++;
        //Else referencePage has free frames
	} else {
            //Insert element at the front of referencePage
            referencePage.push(pageNumber);
            //Increment page fault count.
            pageFaultCount++;
	} 
        /*Debug - Uncomment to print all reference pages for verification*/
        //System.out.println(referencePage);
    }
}
