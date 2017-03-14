import com.gamma.dexter.console.draft.ResponseWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Anirban on 19/01/2017.
 */
public class TestWrapper {
    public static void main(String[] args) {
        TestWrapper testWrapper= new TestWrapper();
        testWrapper.convert();
    }

    public void convert()
    {
        Map<String,String> map= new HashMap<String,String>();
        map.put("Key1","null");
        map.put("Key2",null);
        ResponseWrapper wrapper = new ResponseWrapper();
        wrapper.addPayload(map);
        System.out.println();
    }



}
