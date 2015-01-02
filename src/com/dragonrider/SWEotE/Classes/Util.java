package com.dragonrider.SWEotE.Classes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.Editable;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TextView.BufferType;

import org.xml.sax.XMLReader;

public class Util {
    public static String convertStreamToString(InputStream is) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        reader.close();
        return sb.toString();
    }
    public static String getStringFromFile (String filePath) throws Exception {
        File fl = new File(filePath);
        FileInputStream fin = new FileInputStream(fl);
        String ret = convertStreamToString(fin);
        //Make sure you close all streams.
        fin.close();
        return ret;
    }





    public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
	    ImageView bmImage;

	    public DownloadImageTask(ImageView bmImage) {
	        this.bmImage = bmImage;
	    }

	    protected Bitmap doInBackground(String... urls) {
	        String urldisplay = urls[0];
	        Bitmap mIcon11 = null;
	        try {
	            InputStream in = new java.net.URL(urldisplay).openStream();
	            mIcon11 = BitmapFactory.decodeStream(in);
	        } catch (Exception e) {
	            Log.e("Error", e.getMessage());
	            e.printStackTrace();
	        }
	        return mIcon11;
	    }

	    protected void onPostExecute(Bitmap result) {
	        bmImage.setImageBitmap(result);
	    }
	    
	    
	}
	
	public static Bitmap decodeUri(Uri selectedImage, Context context) throws FileNotFoundException {

        // Decode image size
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(context.getContentResolver().openInputStream(selectedImage), null, o);

        // The new size we want to scale to
        final int REQUIRED_SIZE = 140;

        // Find the correct scale value. It should be the power of 2.
        int width_tmp = o.outWidth, height_tmp = o.outHeight;
        int scale = 1;
        while (true) {
            if (width_tmp / 2 < REQUIRED_SIZE
               || height_tmp / 2 < REQUIRED_SIZE) {
                break;
            }
            width_tmp /= 2;
            height_tmp /= 2;
            scale *= 2;
        }

        // Decode with inSampleSize
        BitmapFactory.Options o2 = new BitmapFactory.Options();
        o2.inSampleSize = scale;
        return BitmapFactory.decodeStream(context.getContentResolver().openInputStream(selectedImage), null, o2);

    }
	
	
	
	public static Typeface getSymbolTypeface() {
		return Typeface.createFromAsset(App.getContext().getAssets(), "fonts/symbol.otf");
	}
	
	public static void updateTextViewSymbols (TextView txt, String Description) {
		setTextViewSymbols(txt, txt.getText().toString());
	}


    public static void setTextViewSymbols(TextView txt, String Description, StandardTagHangler customTagHandler) {

        String newDescription = Description.replace('[', '<').replace(']', '>');

        Spanned spanned = Html.fromHtml(newDescription, null, customTagHandler);

        txt.setText(spanned, BufferType.SPANNABLE);
    }

	public static void setTextViewSymbols(TextView txt, String Description) {


        setTextViewSymbols(txt, Description, new StandardTagHangler());



	}



    public static class StandardTagHangler implements Html.TagHandler {
        @Override
        public void handleTag(boolean opening, String s, Editable editable, XMLReader xmlReader) {


            if (("[" + s + "]").equals(RollResult.DiceCodeBoost) && opening)
            {
                editable.append("b");
                editable.setSpan(new CustomTypefaceSpan(getSymbolTypeface()), editable.length() - 1, editable.length(), 0);
                editable.setSpan(new ForegroundColorSpan(Color.CYAN), editable.length() - 1, editable.length(), 0);
            }

            else if (("[" + s + "]").equals(RollResult.DiceCodeSetback) && opening)
            {
                editable.append("b");
                editable.setSpan(new CustomTypefaceSpan(getSymbolTypeface()), editable.length() - 1, editable.length(), 0);
                editable.setSpan(new ForegroundColorSpan(Color.BLACK), editable.length() - 1, editable.length(), 0);
            }

            else if (("[" + s + "]").equals(RollResult.DiceCodeAbility) && opening)
            {
                editable.append("d");
                editable.setSpan(new CustomTypefaceSpan(getSymbolTypeface()), editable.length() - 1, editable.length(), 0);
                editable.setSpan(new ForegroundColorSpan(Color.GREEN), editable.length() - 1, editable.length(), 0);
            }

            else if (("[" + s + "]").equals(RollResult.DiceCodeDifficulty) && opening)
            {
                editable.append("d");
                editable.setSpan(new CustomTypefaceSpan(getSymbolTypeface()), editable.length() - 1, editable.length(), 0);
                editable.setSpan(new ForegroundColorSpan(Color.MAGENTA), editable.length() - 1, editable.length(), 0);
            }

            else if (("[" + s + "]").equals(RollResult.DiceCodeProficiency) && opening)
            {
                editable.append("c");
                editable.setSpan(new CustomTypefaceSpan(getSymbolTypeface()), editable.length() - 1, editable.length(), 0);
                editable.setSpan(new ForegroundColorSpan(Color.YELLOW), editable.length() - 1, editable.length(), 0);
            }

            else if (("[" + s + "]").equals(RollResult.DiceCodeChallenge) && opening)
            {
                editable.append("c");
                editable.setSpan(new CustomTypefaceSpan(getSymbolTypeface()), editable.length() - 1, editable.length(), 0);
                editable.setSpan(new ForegroundColorSpan(Color.RED), editable.length() - 1, editable.length(), 0);
            }

            else if (("[" + s + "]").equals(RollResult.DiceCodeForce) && opening)
            {
                editable.append("c");
                editable.setSpan(new CustomTypefaceSpan(getSymbolTypeface()), editable.length() - 1, editable.length(), 0);
                editable.setSpan(new ForegroundColorSpan(Color.LTGRAY), editable.length() - 1, editable.length(), 0);
            }


            else if (("[" + s + "]").equals(RollResult.DiceCodeAdvantage) && opening)
            {
                editable.append("a");
                editable.setSpan(new CustomTypefaceSpan(getSymbolTypeface()), editable.length() - 1, editable.length(), 0);
            }

            else if (("[" + s + "]").equals(RollResult.DiceCodeSuccess) && opening)
            {
                editable.append("s");
                editable.setSpan(new CustomTypefaceSpan(getSymbolTypeface()), editable.length() - 1, editable.length(), 0);
            }

            else if (("[" + s + "]").equals(RollResult.DiceCodeThreat) && opening)
            {
                editable.append("t");
                editable.setSpan(new CustomTypefaceSpan(getSymbolTypeface()), editable.length() - 1, editable.length(), 0);
            }

            else if (("[" + s + "]").equals(RollResult.DiceCodeFailure) && opening)
            {
                editable.append("f");
                editable.setSpan(new CustomTypefaceSpan(getSymbolTypeface()), editable.length() - 1, editable.length(), 0);
            }

            else if (("[" + s + "]").equals(RollResult.DiceCodeTriumph) && opening)
            {
                editable.append("x");
                editable.setSpan(new CustomTypefaceSpan(getSymbolTypeface()), editable.length() - 1, editable.length(), 0);
            }

            else if (("[" + s + "]").equals(RollResult.DiceCodeDespair) && opening)
            {
                editable.append("y");
                editable.setSpan(new CustomTypefaceSpan(getSymbolTypeface()), editable.length() - 1, editable.length(), 0);
            }

            else if (("[" + s + "]").equals(RollResult.DiceCodeLight) && opening)
            {
                editable.append("Z");
                editable.setSpan(new CustomTypefaceSpan(getSymbolTypeface()), editable.length() - 1, editable.length(), 0);
            }

            else if (("[" + s + "]").equals(RollResult.DiceCodeDark) && opening)
            {
                editable.append("z");
                editable.setSpan(new CustomTypefaceSpan(getSymbolTypeface()), editable.length() - 1, editable.length(), 0);
            }
            else
                Log.i("HoplaTag", "Tag=" + s);



        }
    }
	

}
