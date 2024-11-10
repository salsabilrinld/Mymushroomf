package com.example.mymushroomf;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

public class SettingsActivity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity); // Set the layout for the activity

        // Setup action bar for back navigation
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);  // Enable back button
        }

        // If this is the first time the activity is created, load the fragment
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.settings, new SettingsFragment())
                    .commit();
        }
    }

    // SettingsFragment to display preferences
    public static class SettingsFragment extends PreferenceFragmentCompat {

        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            // Load the preferences from the resource file
            setPreferencesFromResource(R.xml.root_preferences, rootKey);

            // Find the 'logout' preference using the newer method (avoids deprecation warning)
            Preference logoutPreference = findPreference("logout");

            // Set a listener for the logout preference
            if (logoutPreference != null) {
                logoutPreference.setOnPreferenceClickListener(preference -> {
                    // Show a toast message when logout is clicked
                    Toast.makeText(requireActivity(), "Keluar", Toast.LENGTH_SHORT).show();

                    // Redirect to the login activity and clear the task stack
                    Intent intent = new Intent(requireActivity(), formlogin.class); // Make sure this is correct
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    requireActivity().startActivity(intent);

                    // Optionally finish the current activity to prevent the user from navigating back to it
                    requireActivity().finish();

                    return true;  // Return true to indicate the preference click was handled
                });
            }
        }
    }
}
