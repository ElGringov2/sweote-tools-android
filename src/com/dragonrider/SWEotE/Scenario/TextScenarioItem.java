package com.dragonrider.SWEotE.Scenario;

import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dragonrider.SWEotE.Classes.CustomTypefaceSpan;
import com.dragonrider.SWEotE.Classes.DiceRoller;
import com.dragonrider.SWEotE.Classes.RollResult;
import com.dragonrider.SWEotE.Classes.Skill;
import com.dragonrider.SWEotE.Classes.Util;
import com.dragonrider.SWEotE.R;

import org.xml.sax.XMLReader;

import java.lang.reflect.Field;
import java.util.HashMap;

public class TextScenarioItem extends ScenarioItem {
    public String Text;


    @Override
    public View getView(LayoutInflater inflater, ViewGroup parent) {
        View convertView = inflater.inflate(R.layout.scenarioitem_text, parent, false);


        TextView textView1 = (TextView) convertView.findViewById(R.id.textView);
        TextView textView2 = (TextView) convertView.findViewById(R.id.textView2);



        textView1.setText(Name);
        Util.setTextViewSymbols(textView2, Text, new ScenarioItemCustomTagHandler(inflater.getContext()));


        return convertView;
    }


    private class ScenarioItemCustomTagHandler extends Util.StandardTagHangler {

        private Context context;

        private ScenarioItemCustomTagHandler(Context localContext) {
            context = localContext;
        }


        @Override
        public void handleTag(boolean opening, String s, Editable editable, XMLReader xmlReader) {
            if (s.equals("skillroll") && opening) {
                HashMap<String, String> attrs = processAttributes(xmlReader);


                int diff = Integer.valueOf(attrs.get("difficulty"));
                int chall = Integer.valueOf(attrs.get("upgrade"));
                int boost = Integer.valueOf(attrs.get("boost"));
                int setback = Integer.valueOf(attrs.get("setback"));


                Skill skill = Skill.getNewSkill(Integer.valueOf(attrs.get("id")));



                String insertString = skill.getName();
                if (diff == 1) insertString += " facile";
                if (diff == 2) insertString += " moyen";
                if (diff == 3) insertString += " difficile";
                if (diff == 4) insertString += " très difficile";
                if (diff == 5) insertString += " impossible";

                if (chall > 0)
                    insertString += " amélioré " + String.valueOf(chall) + " fois (";
                else
                    insertString += "(";


                editable.append(insertString);
                editable.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), editable.length() - insertString.length(), editable.length(), 0);

                final RollResult rr = new RollResult();
                rr.DiceDifficulty = diff;
                rr.UpgradeNegativePool(chall);
                rr.DiceBoost = boost;
                rr.DiceSetback = setback;



                insertString = "";
                for (int iDices = 0; iDices < rr.DiceDifficulty; iDices++)
                    insertString += "d";
                editable.append(insertString);
                editable.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), editable.length() - insertString.length(), editable.length(), 0);
                editable.setSpan(new CustomTypefaceSpan(Util.getSymbolTypeface()), editable.length() - insertString.length(), editable.length(), 0);
                editable.setSpan(new ForegroundColorSpan(Color.MAGENTA), editable.length() - insertString.length(), editable.length(), 0);
                insertString = "";
                for (int iDices = 0; iDices < rr.DiceChallenge; iDices++)
                    insertString += "c";
                editable.append(insertString);
                editable.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), editable.length() - insertString.length(), editable.length(), 0);
                editable.setSpan(new CustomTypefaceSpan(Util.getSymbolTypeface()), editable.length() - insertString.length(), editable.length(), 0);
                editable.setSpan(new ForegroundColorSpan(Color.RED), editable.length() - insertString.length(), editable.length(), 0);
                insertString = "";
                for (int iDices = 0; iDices < rr.DiceBoost; iDices++)
                    insertString += "b";
                editable.append(insertString);
                editable.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), editable.length() - insertString.length(), editable.length(), 0);
                editable.setSpan(new CustomTypefaceSpan(Util.getSymbolTypeface()), editable.length() - insertString.length(), editable.length(), 0);
                editable.setSpan(new ForegroundColorSpan(Color.CYAN), editable.length() - insertString.length(), editable.length(), 0);
                insertString = "";
                for (int iDices = 0; iDices < rr.DiceSetback; iDices++)
                    insertString += "b";
                editable.append(insertString);
                editable.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), editable.length() - insertString.length(), editable.length(), 0);
                editable.setSpan(new CustomTypefaceSpan(Util.getSymbolTypeface()), editable.length() - insertString.length(), editable.length(), 0);
                editable.setSpan(new ForegroundColorSpan(Color.BLACK), editable.length() - insertString.length(), editable.length(), 0);



                insertString = ")";
                editable.append(insertString);
                editable.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), editable.length() - insertString.length(), editable.length(), 0);





            }
            else
                super.handleTag(opening, s, editable, xmlReader);
        }



        private HashMap<String, String> processAttributes(final XMLReader xmlReader) {
            HashMap<String, String> attributes = new HashMap<String, String>();
            try {
                Field elementField = xmlReader.getClass().getDeclaredField("theNewElement");
                elementField.setAccessible(true);
                Object element = elementField.get(xmlReader);
                Field attsField = element.getClass().getDeclaredField("theAtts");
                attsField.setAccessible(true);
                Object atts = attsField.get(element);
                Field dataField = atts.getClass().getDeclaredField("data");
                dataField.setAccessible(true);
                String[] data = (String[])dataField.get(atts);
                Field lengthField = atts.getClass().getDeclaredField("length");
                lengthField.setAccessible(true);
                int len = (Integer)lengthField.get(atts);

                /**
                 * MSH: Look for supported attributes and add to hash map.
                 * This is as tight as things can get :)
                 * The data index is "just" where the keys and values are stored.
                 */
                for(int i = 0; i < len; i++)
                    attributes.put(data[i * 5 + 1], data[i * 5 + 4]);
            }
            catch (Exception e) {
                Log.d("hopla", "Exception: " + e);
            }

            return attributes;
        }
    }
}
