TimelyTextView
==============

Animated TextView like Timely app


v1 : 
Thanks to Malinskiy, the project now has Gradle/Maven support !  Few things that have been fixed as well :

- The layout_height & layout_width parameters are now supported. 
- Animate between any two numbers(no need for consecutive numbers, any longer)
- Sample demo available in the repository 

Again a huge thanks to Malinskiy(https://github.com/Malinskiy) for the effort !

v0 :
Intital commit for Animated TextView present in the Timely(Alarm) App.


This is just an addition to the concept explained by Sriram Ramani here : http://sriramramani.wordpress.com/2013/10/14/number-tweening/

I have just figured out missing bits & pieces and made a simple library out of it. Please thank Sriram if this helped you. Also please note I have been very busy and this was the outcome  of just 2hrs of work on a lazy Monday afternoon, so there might be a few bugs. It would be great if anyone else wants to contribute and take this to the next level. Have a few ideas in mind already, feel free to get in touch and send Pull Requests.


Usage :

XML Layout -

     <com.github.adnansm.timelytextview.TimelyView
            android:id="@+id/textView1"
            android:layout_width="wrap_content"
            android:layout_height="40dp"
            android:layout_gravity="center"
            android:layout_margin="@dimen/activity_vertical_margin"
            />
   

Java -

         public class MainActivity extends Activity {
         private TimelyView timelyView; 
                   @Override
                   protected void onCreate(Bundle savedInstanceState) {
                         super.onCreate(savedInstanceState);
                         setContentView(R.layout.activity_main);
                         timelyView = (TimelyView) findViewById(R.id.textView1);
                        
                   }
        }


License
--------

    Copyright 2013 Adnan A M.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.



[![Bitdeli Badge](https://d2weczhvl823v0.cloudfront.net/adnan-SM/timelytextview/trend.png)](https://bitdeli.com/free "Bitdeli Badge")

