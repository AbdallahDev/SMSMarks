package com.example.user.smsmarks;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.widget.Toast;

/**
 * Created by user on 5/28/2017.
 */
public class SMSReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {

        Bundle bundle=intent.getExtras();
        SmsMessage[] msgs=null;
        String text="";

        if(bundle!=null)
        {
            Object[] pdus=(Object[])bundle.get("pdus");
            msgs=new SmsMessage[pdus.length];
            for(int x=0;x<msgs.length;x++)
            {
                msgs[x]=SmsMessage.createFromPdu((byte[])pdus[x]);
                text+= msgs[x].getMessageBody();
            }

            Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
            Toast.makeText(context, msgs[0].getOriginatingAddress()
                    , Toast.LENGTH_SHORT).show();

            StudentDB obj=new StudentDB(context);
            SQLiteDatabase db=obj.getReadableDatabase();
            Cursor cur=db.rawQuery("select marks from student where mobile=?",
                    new String[]{msgs[0].getOriginatingAddress()});
            if(cur.getCount()>0)
            {
                cur.moveToFirst();
                String m=cur.getString(0);

                SmsManager manager=SmsManager.getDefault();
                manager.sendTextMessage(msgs[0].getOriginatingAddress(),null,
                        m,null,null);
            }
        }
    }
}
