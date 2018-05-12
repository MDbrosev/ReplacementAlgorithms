import java.util.Random;

/**
 *
 * @author Yoseph
 */

public class RandomReplacement extends ReplacementAlgorithm {
    //referencePage will hold the input array
    int[] referencePage;
    ////The intArray size will be defined by pageFrameCount.
    int[] intArray;
    //The page size will be defined by pageFrameCount.
    int[] page;	                
    //The counter will keep track of where the new element will be inserted.
    int counter;        
    //The pointer will keep track of where elements are being inserted.
    int pointer;       
    //The max will hold the largest distance forward of the page whose next use will occur farthest in the future.
    int max;       
    //The maxPointer will keep track of the best victim to replace.
    int maxPointer;
    
    //Constructor, param is pageFrameCount, and arrIn
    public RandomReplacement(int pageFrameCount, int[] arrIn) {
        //Reusing parent class(ReplacementAlgorithm) constructor
        super(pageFrameCount);
        //Storing input
        referencePage = arrIn;
        //Initializing page size
        page = new int[pageFrameCount];
        //Initializing frameSize size
        intArray = new int[pageFrameCount];
        //Initializing counter value
        counter = 0;
        //Initializing pointer value
        pointer = 0;            
        //Initialize the frame size
        for(int i = 0; i < pageFrameCount; i++) {
            //The framePage can not be initialized with a value that might match the input
            //Fill framePage with -2147483648 to allow for full int range from input
            intArray[i] = -2147483648;
            /*-2147483648 can not be in the input*/
        }            
    }       

    //Override superclass method insert() to perform random algorithm
    @Override
    public void insert(int pageNumber) {
        RandomOperation(pageNumber);
    }
    
    public void RandomOperation(int pageNumber) {		
        max = 0;
        //For i is less than the pageFrameCount
        for(int i = 0; i < pageFrameCount; i++) {
            //if the pageNumber is in intArray
            if(intArray[i] == pageNumber) {
                //Increment the pointer
                pointer++;
                return;
            }                        
        }		
	//If the counter is less than pageFrameCount intArray is not full
        if(counter < pageFrameCount) {
            //Add the pageNumber to the end
            intArray[counter] = pageNumber;
            //Increment the counter
            counter++;
            //Increment the pointer
            pointer++;
            //Increment the pageFaultCount
            pageFaultCount++;
            return;
        }
        //get random gumber
	int asd = getRandomNumber(intArray.length);	
	//Replace the reandom victim
        intArray[asd] = pageNumber;
        //Increment the counter
	counter++;
        //Increment the pointer
	pointer++;
        //Increment the pageFaultCount
	pageFaultCount++;		                
    }       
    //Get a random number within a specific range
    private static int getRandomNumber(int max) {
        int min=0;
        if (min >= (max)) {
            throw new IllegalArgumentException("Error!");
        }
        Random r = new Random();
        //possible pages to replace is (max - min)-1        
        return r.nextInt((max - min) - 1);
    }
}
