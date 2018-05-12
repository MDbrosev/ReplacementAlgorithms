package pagereplacementp0010;

import java.util.ArrayDeque;

/**
 *
 * @author Yoseph
 */

public class lruReplacement extends ReplacementAlgorithm {
    /*ArrayDeque is implemented to hold the reference strings because 
    a empty array deque can be constructed with an initial capacity 
    sufficient to hold the specified number of elements.
    It also allows for O(1) addition and removal of first and last elements.
    */
    private final ArrayDeque<Integer> referencePage;
    //frameSize, is an integer that will be used to set the size of referencePage.
    private final int frameSize;
    //Constructor, param is pageFrameCount
    public lruReplacement(int pageFrameCount) {
        //Reusing parent class(ReplacementAlgorithm) constructor
        super(pageFrameCount);
        //Storing the number of physical page frames
        frameSize = pageFrameCount;
        //Initializing referencePage with the page frame size
        referencePage = new ArrayDeque<>(frameSize);
    }
    
    //Override superclass method insert() to perform LRU algorithm
    @Override
    public void insert(int pageNumber) {       
        lruOperation(pageNumber);
    }
    
    private void lruOperation(int pageNumber) {
        //If referencePage contains pageNumber is true
        if(referencePage.contains(pageNumber)) {
            //Remove the first element in referencePage.
            referencePage.remove(pageNumber);
            //Insert the new pageNumber of the same value at the front of referencePage to indicate it was used
            referencePage.push(pageNumber);
            return;
        }		
        //If referencePage is full
        if(referencePage.size() == frameSize) {
            //Remove the last element of referencePage.
            referencePage.removeLast();
            //Insert the new pageNumber at the front of referencePage.
            referencePage.push(pageNumber);
            //Increment pageFaultCount
            pageFaultCount++;
        //Else referencePage has free frames   
        } else {
            //Insert the new pageNumber at the front of referencePage.
            referencePage.push(pageNumber);
            //Increment pageFaultCount
            pageFaultCount++;
        }
        /*Debug - Uncomment to print all reference pages for verification*/
        //System.out.println(referencePage);
    }
}
