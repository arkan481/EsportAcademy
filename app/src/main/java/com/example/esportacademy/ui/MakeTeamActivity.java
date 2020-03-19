package com.example.esportacademy.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.esportacademy.R;
import com.example.esportacademy.interfaces.maketeaminterface;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;

public class MakeTeamActivity extends AppCompatActivity implements maketeaminterface {

    private LinearLayout lrgen,lrach,lrgall,lrmem;
    private RelativeLayout rltopmt;
    private ImageView ivbackbutton,ivline1,ivline2,ivline3,ivline4,ivcreatebtn;
    private EditText etteamname;
    private ImageView ivcamerateamlogo;
    private String description;
    private ArrayList<String>games = new ArrayList<>();
    private ArrayList<String>achievements = new ArrayList<>();
    private ArrayList<String>achievementsdesc = new ArrayList<>();
    private ArrayList<String>member = new ArrayList<>();
    private ArrayList<String>memberdesc = new ArrayList<>();
    private static final int IMAGE_PICK_CODE = 1000;
    private static final int IMAGE_PICK_CODE_2 = 1002;
    private static final int PERMISSION_PICK_CODE =1001;
    private boolean memUpdating,genUpdating,achUpdating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_team);
        lrgen = findViewById(R.id.LRgenmtid);
        lrach = findViewById(R.id.LRachhmtid);
        rltopmt = findViewById(R.id.Rltopmt);
        ivcamerateamlogo = findViewById(R.id.ivteamcameralogo);
        lrgall = findViewById(R.id.LRgallerymtid);
        etteamname=findViewById(R.id.etteamname);
        lrmem = findViewById(R.id.LRmembemtid);
        ivcreatebtn = findViewById(R.id.ivcreatebtn);
        lrgen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstfragment();
            }
        });
        lrach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                secondfragment();
            }
        });
        lrgall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thirdfragment();
            }
        });
        lrmem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fourthfragment();
            }
        });
        ivbackbutton = findViewById(R.id.ivbackbuttonmt);
        ivline1 = findViewById(R.id.ivline1);
        ivline2 = findViewById(R.id.ivline2);
        ivline3 = findViewById(R.id.ivline3);
        ivline4 = findViewById(R.id.ivline4);
        lrgen.performClick();
        ivbackbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ivcreatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dummyfragment();
                new AlertDialog.Builder(MakeTeamActivity.this).setTitle("Confirm Customization").setMessage("Confirm Your Team Customization?").setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = new Intent(MakeTeamActivity.this,CreateTeamActivity.class);
                        intent.putExtra("teamname",etteamname.getText().toString());
                        new CreateTeamActivity(MakeTeamActivity.this);
                        startActivity(intent);

                    }
                }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
            }
        });
        ivcamerateamlogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prepareToGallery(IMAGE_PICK_CODE);
            }
        });
        rltopmt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prepareToGallery(IMAGE_PICK_CODE_2);
            }
        });
    }

    private void prepareToGallery(int pickcode) {
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(MakeTeamActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED) {
                pickImage(pickcode);
            }else {
                String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                requestPermissions(permissions,PERMISSION_PICK_CODE);
            }
        }else {
            pickImage(pickcode);
        }
    }

    private void pickImage(int pickcode) {
        if (pickcode==IMAGE_PICK_CODE) {
            Intent intenToGallery = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intenToGallery.setType("image/*");
            startActivityForResult(intenToGallery,IMAGE_PICK_CODE);
        }else {
            Intent intenToGallery = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intenToGallery.setType("image/*");
            startActivityForResult(intenToGallery,IMAGE_PICK_CODE_2);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_PICK_CODE:
                if (grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED) {
                    pickImage(1000);
                }else {
                    Toast.makeText(MakeTeamActivity.this,"Permission Denied",Toast.LENGTH_LONG).show();
                }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            Uri test = data.getData();

            ivcamerateamlogo.setImageURI(test);
        }else if (resultCode==RESULT_OK&&requestCode==IMAGE_PICK_CODE_2) {
            Uri test2 = data.getData();
            ContentResolver contentResolver = getContentResolver();
            try {
                InputStream is = contentResolver.openInputStream(test2);
                Drawable drawable = Drawable.createFromStream(is,test2.toString());
                rltopmt.setBackground(drawable);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void turnlineon(int i) {
        switch (i) {
            case 1:
                ivline1.setVisibility(View.VISIBLE);
                ivline2.setVisibility(View.GONE);
                ivline3.setVisibility(View.GONE);
                ivline4.setVisibility(View.GONE);
                break;
            case 2:
                ivline1.setVisibility(View.GONE);
                ivline2.setVisibility(View.VISIBLE);
                ivline3.setVisibility(View.GONE);
                ivline4.setVisibility(View.GONE);
                break;
            case 3:
                ivline1.setVisibility(View.GONE);
                ivline2.setVisibility(View.GONE);
                ivline3.setVisibility(View.VISIBLE);
                ivline4.setVisibility(View.GONE);
                break;
            case 4:
                ivline1.setVisibility(View.GONE);
                ivline2.setVisibility(View.GONE);
                ivline3.setVisibility(View.GONE);
                ivline4.setVisibility(View.VISIBLE);
                break;
            default:
                ivline1.setVisibility(View.VISIBLE);
                ivline2.setVisibility(View.GONE);
                ivline3.setVisibility(View.GONE);
                ivline4.setVisibility(View.GONE);
                break;
        }
    }
    private void firstfragment() {
        turnlineon(1);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.FRmaketeam,new FragmentGeneral(this),"general");
        fragmentTransaction.commit();
    }
    private void secondfragment() {
        turnlineon(2);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.FRmaketeam,new FragmentAchievement(this));
        fragmentTransaction.commit();
    }
    private void thirdfragment() {
        turnlineon(3);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.FRmaketeam,new GalleryFragment(this));
        fragmentTransaction.commit();
    }
    private void fourthfragment() {
        turnlineon(4);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.FRmaketeam,new MemberFragment(this));
        fragmentTransaction.commit();
    }
    private void dummyfragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.FRmaketeam,new DummyFragment());
        fragmentTransaction.commit();
    }

    @Override
    public void setDescription(String desc) {
        this.description=desc;
    }

    @Override
    public void setGenUpdating(boolean updating) {
        this.genUpdating=updating;
    }

    @Override
    public void setAchUpdating(boolean updating) {
        this.achUpdating=updating;
    }

    @Override
    public void setMemberUpdating(boolean updating) {
        this.memUpdating=updating;
    }

    @Override
    public void setGames(ArrayList<String>games) {
        this.games=games;
    }

    @Override
    public void setAchievement(ArrayList<String> ach) {
        this.achievements=ach;
    }

    @Override
    public void setaAhievementDesc(ArrayList<String> achDesc) {
        this.achievementsdesc=achDesc;
    }

    @Override
    public void setMember(ArrayList<String> member) {
        this.member=member;
    }

    @Override
    public void setMemberDesc(ArrayList<String> memberDesc) {
        this.memberdesc=memberDesc;
    }

    @Override
    public String getDesc() {
        return this.description;
    }

    @Override
    public ArrayList<String> getGames() {
        return this.games;
    }

    @Override
    public ArrayList<String> getAchievement() {
        return this.achievements;
    }

    @Override
    public ArrayList<String> getAchDesc() {
        return this.achievementsdesc;
    }

    @Override
    public ArrayList<String> getMember() {
        return this.member;
    }

    @Override
    public ArrayList<String> getMemberDesc() {
        return this.memberdesc;
    }

    @Override
    public boolean getGenUpdating() {
        return this.genUpdating;
    }

    @Override
    public boolean getAchUpdating() {
        return this.achUpdating;
    }

    @Override
    public boolean getMemberUpdating() {
        return this.memUpdating;
    }
}
