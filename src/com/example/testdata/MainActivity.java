package com.example.testdata;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.os.Build;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
            
        }
        TextView tv = (TextView)findViewById(R.id.fip);
        Log.d("ActivityThread", "-------->Testdata onCreate");
        try {
			File file = new File("/sdcard/ccb_net.ks");
			if(file.getParentFile().exists() == false){
				file.getParentFile().mkdirs();
			}
			if(file.exists() == false){
				file.createNewFile();
			}
//			Runtime.getRuntime().exec("chmod -R 777 /data/tmp/epay");
			
			FileOutputStream fop = new FileOutputStream(file);
			fop.write("my test\r\n".getBytes());
			fop.flush();
			fop.close();
			
			FileInputStream fip = new FileInputStream(file);			
			int cnt=0;
	        while((cnt=fip.read())!=-1){
	            System.out.println((char)cnt);//一次只能读一个字节
	        }
	        //byte[] inOutb = new byte[fip.available()];
			//fis.read(bbuf)
			//tv.setText(inOutb.toString());
			//fip.flush();
			fip.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
       
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

}
