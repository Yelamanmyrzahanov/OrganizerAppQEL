package kz.djunglestones.organizerappqel.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.Objects;

import jp.wasabeef.richeditor.RichEditor;
import kz.djunglestones.organizerappqel.R;

public class EventDescriptionActivity extends AppCompatActivity{
    private Toolbar toolbar;
    private EditText editText;
    private static final String BOLD = "<b>Bold</b><br><br>";
    private static final String ITALIT = "<i>Italic</i><br><br>";
    private static final String UNDERLINE = "<u>Underline</u><br><br>";
    private static final String BULLET = "<ul><li>asdfg</li></ul>";
    private static final String LINK = "<a href=\"https://github.com/mthli/Knife\">Link</a><br><br>";
    private static final String EXAMPLE = BOLD + ITALIT + UNDERLINE + BULLET + LINK;
    private RichEditor richEditor;
//    RichEditText mRichEditText;
    private String lastAction;

//    private int buttonsIds[]= {R.id.allCaps_txt_btn,R.id.bold_txt_btn,R.id.italic_txt_btn,R.id.list_dotted_txt_btn,R.id.list_numbered_txt_btn,R.id.redo_txt_btn,R.id.insert_link};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_description);

        initUI();
        setupStatusBar();
        setupToolbar();
        richEditor = findViewById(R.id.rich_text_editor);
        richEditor.setEditorHeight(200);
        richEditor.setEditorFontSize(16);
        richEditor.setPadding(10,10,10,10);
        richEditor.setPlaceholder("Введите описание...");


//        mRichEditText = (RichEditText) findViewById(R.id.rich_edit_text);
//        RichTextActions richTextActions = (RichTextActions) findViewById(R.id.rich_text_actions);
//
//        mRichEditText.setRichTextActionsView(richTextActions);
//        mRichEditText.setPreviewText("Выберите цвет");
//        mRichEditText.setHint("Описание");

        if (savedInstanceState != null) {
            richEditor.restoreState(savedInstanceState);
        }

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            if (0 != (getApplication().getApplicationInfo().flags &= ApplicationInfo.FLAG_DEBUGGABLE)) {
//                WebView.setWebContentsDebuggingEnabled(true);
//            }
//        }


//        if (savedInstanceState != null) {
//            richEditor.restoreState(savedInstanceState);
//        }

//        knife = (KnifeText) findViewById(R.id.knife);
//        // ImageGetter coming soon...
//        knife.fromHtml(EXAMPLE);
//        knife.setSelection(knife.getEditableText().length());
//
//
        setupBold();
        setupItalic();
        setupBullet();
        setupUndo();
        setNumbers();
        setTextSize();
        insertLink();



    }

    private void insertLink() {
        ImageButton insertLink = findViewById(R.id.insert_link);
        insertLink.setOnClickListener(v ->
                showLinkDialog());
    }

    private void showLinkDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);

        View view = getLayoutInflater().inflate(R.layout.dialog_link,null,false);
        final EditText editTextLink = view.findViewById(R.id.edit);
        builder.setView(view);
        builder.setTitle(R.string.dialog_title);

        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String link = editTextLink.getText().toString();

                if (TextUtils.isEmpty(link)) {
                    return;
                }

                // When KnifeText lose focus, use this method
//                knife.link(link, start, end);
                Toast.makeText(getApplicationContext(),"Link Clicked "+link,Toast.LENGTH_SHORT).show();
                richEditor.insertLink(link,"Ссылка");
            }
        });

        builder.setNegativeButton(R.string.close_toggle, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // DO NOTHING HERE
            }
        });

        builder.create().show();
    }

    private void setTextSize() {
        ImageButton textSize = findViewById(R.id.allCaps_txt_btn);
        final boolean[] isChanged = new boolean[1];
        textSize.setOnClickListener(v -> {
            final String[] chooseTicketTypeArr = EventDescriptionActivity.this.getResources().getStringArray(R.array.headingTypeArr);
            final AlertDialog.Builder builder = new AlertDialog.Builder(EventDescriptionActivity.this);
            builder.setTitle(R.string.headingType)
                    .setItems(R.array.headingTypeArr, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            lastAction = "setHeading";
                            richEditor.setHeading(which+1);
                        }
                    });
            builder.create();
            builder.show();
        });
    }

    private void setNumbers() {
        ImageButton numberedList = findViewById(R.id.list_numbered_txt_btn);
        numberedList.setOnClickListener(v -> {
            lastAction = "setNumbers";
            richEditor.setNumbers();
        });
    }



    private void setupBullet() {
        ImageButton dottedList = findViewById(R.id.list_dotted_txt_btn);
        dottedList.setOnClickListener(v -> {
            lastAction = "setBullets";
            richEditor.setBullets();
        });
    }



    private void setupUndo() {
        ImageButton redo = findViewById(R.id.redo_txt_btn);
        redo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                richEditor.undo();
            }
        });
    }
//
//
    private void setupBold() {
        ImageButton bold = findViewById(R.id.bold_txt_btn);
        bold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastAction = "setBold";
                richEditor.setBold();

//                bold.setSelected(!bold.isSelected());
//                if (bold.isSelected()){
//                    bold.setImageResource(R.drawable.ic_format_bold_pressed_24dp);
//                    richEditor.setBold();
//                }
//                else{
//                    richEditor.setBold();
//                    bold.setImageResource(R.drawable.ic_format_bold_black_24dp);
//                }

            }
        });
    }
//
    private void setupItalic() {
        ImageButton italic = findViewById(R.id.italic_txt_btn);
        italic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastAction = "setBold";
                richEditor.setItalic();

            }
        });
    }










//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        if (mRichEditText != null) {
////            mRichEditText.saveState(outState);
//        }
//        super.onSaveInstanceState(outState);
//    }

    private void initUI() {
        toolbar = findViewById(R.id.toolbar_event_desc);
//        editText = findViewById(R.id.edittext_desc_event);
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void setupStatusBar() {
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.event_descr_activity_menu, menu);
        MenuItem save = menu.findItem(R.id.save_event_descr_menu);
        save.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                String text = richEditor.getHtml();
                Toast.makeText(EventDescriptionActivity.this,text,Toast.LENGTH_SHORT).show();
//                System.out.println(richEditor.getHtml());
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (richEditor!= null) {
            richEditor.saveState(outState);
        }
    }

//    @Override
//    public void onClick(View v) {
//        String text = editText.getText().toString();
//
//        int startSelection=editText.getSelectionStart();
//        int endSelection=editText.getSelectionEnd();
//
//        String selectedText = editText.getText().toString().substring(startSelection, endSelection);
//        Log.d("SELECTED TEXT", "onClick: "+selectedText);
//        if (!text.isEmpty()){
//            switch (v.getId()){
//                case R.id.bold_txt_btn:
//                    text = text.replace(selectedText , "<b>"+ (selectedText + "</b>"));
//                    editText.setText(Html.fromHtml(text));
//                    Toast.makeText(EventDescriptionActivity.this,"BOlD "+selectedText,Toast.LENGTH_SHORT).show();
//                    break;
//                case R.id.italic_txt_btn:
//                    Toast.makeText(EventDescriptionActivity.this,"Italic "+selectedText,Toast.LENGTH_SHORT).show();
//                    text = text.replace(selectedText , "<i>"+ (selectedText + "</i>"));
//                    editText.setText(Html.fromHtml(text));
//                    break;
//                case R.id.allCaps_txt_btn:
//                    break;
//                case R.id.insert_link:
//                    break;
//                case R.id.list_numbered_txt_btn:
//                    break;
//                case R.id.list_dotted_txt_btn:
//                    break;
//                case R.id.redo_txt_btn:
//                    break;
//            }
//        }
//
//    }
}
