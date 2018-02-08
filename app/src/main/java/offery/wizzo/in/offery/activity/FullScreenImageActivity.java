/*******************************************************************************
 * Copyright 2011-2013 Sergey Tarasevich
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package offery.wizzo.in.offery.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

import offery.wizzo.in.offery.R;


public class FullScreenImageActivity extends Activity {
    LinearLayout linerMainFullImageId;
    ImageView thumbimage, close;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_image);
        thumbimage = (ImageView) findViewById(R.id.imgThumbImageId);
        close = (ImageView) findViewById(R.id.imgCancleId);
        Intent intent = getIntent();
        String img_utl = intent.getStringExtra("img_url");
        try {
            if (img_utl != null & !img_utl.equals("")) {
                Glide.with(FullScreenImageActivity.this).load(img_utl)
                        .error(R.drawable.ic_gallery).placeholder(R.drawable.ic_gallery).into(thumbimage);

            }
        } catch (Exception e) {
        }
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}