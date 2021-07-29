package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import java.text.MessageFormat;

public class NeighbourProfileActivity extends AppCompatActivity {

    private Neighbour neighbour;
    private NeighbourApiService apiService;
    private FloatingActionButton fav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        setContentView(R.layout.activity_neighbour_profile);
        apiService = DI.getNeighbourApiService();
        fav = findViewById(R.id.favoritesFab);

        if (getIntent().hasExtra("neighbour")) {
            neighbour = getIntent().getParcelableExtra("neighbour");
            getNeighbourAvatar();
            getNeighbourDetails();
            setFavoriteStatus();
            addOrDeleteFavorite();
            pressBack();
        }
    }

    private void getNeighbourAvatar() {
        ImageView avatar = findViewById(R.id.avatar);
        Glide.with(this)
                .load(neighbour.getAvatarUrl())
                .into(avatar);
    }

    // Get neighbour profile instance with details
    private void getNeighbourDetails() {
        TextView name = findViewById(R.id.name);
        name.setText(neighbour.getName());
        TextView name2 = findViewById(R.id.name2);
        name2.setText(neighbour.getName());
        TextView address = findViewById(R.id.address);
        address.setText(neighbour.getAddress());
        TextView phoneNumber = findViewById(R.id.phoneNumber);
        phoneNumber.setText(neighbour.getPhoneNumber());
        TextView socialMedia = findViewById(R.id.socialMedia);
        socialMedia.setText(MessageFormat.format("www.facebook.fr/{0}", neighbour.getName().toLowerCase()));
        TextView aboutMe = findViewById(R.id.aboutMe);
        aboutMe.setText(neighbour.getAboutMe());
    }

    // Set favorites fab change, depending on whether neighbour is fav or not
    private void setFavoriteStatus() {
        if (apiService.getFavorites().contains(neighbour)) {
            fav.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_star_yellow_24dp));
        } else {
            fav.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_star_border_yellow_24dp));
        }
    }

    // Add & delete neighbour from favorites list
    private void addOrDeleteFavorite() {
        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (apiService.getFavorites().contains(neighbour)) {
                    apiService.deleteFavorite(neighbour);
                    fav.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_star_border_yellow_24dp));
                    Toast.makeText(getApplicationContext(), "Voisin retiré de la liste de vos favoris", Toast.LENGTH_SHORT).show();
                } else {
                    apiService.addFavorite(neighbour);
                    fav.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_star_yellow_24dp));
                    Toast.makeText(getApplicationContext(), "Voisin ajouté à la liste de vos favoris", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Set transparent toolbar w/ up arrow
    private void pressBack() {
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        final Drawable upArrow = getResources().getDrawable(R.drawable.baseline_arrow_back_24);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
    }
}