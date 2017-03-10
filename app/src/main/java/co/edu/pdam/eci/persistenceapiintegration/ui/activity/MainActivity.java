package co.edu.pdam.eci.persistenceapiintegration.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import co.edu.pdam.eci.persistenceapiintegration.R;
import co.edu.pdam.eci.persistenceapiintegration.data.DBException;
import co.edu.pdam.eci.persistenceapiintegration.data.OrmModel;
import co.edu.pdam.eci.persistenceapiintegration.data.dao.TeamDao;
import co.edu.pdam.eci.persistenceapiintegration.data.entity.Team;
import co.edu.pdam.eci.persistenceapiintegration.network.NetworkException;
import co.edu.pdam.eci.persistenceapiintegration.network.RequestCallback;
import co.edu.pdam.eci.persistenceapiintegration.network.RetrofitNetwork;

public class MainActivity extends AppCompatActivity {

    OrmModel om;
    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        om = new OrmModel();
        om.init(getApplicationContext());
        try {
            ExecutorService executorService = Executors.newFixedThreadPool(1);
            executorService.execute( new Runnable()
            {
                @Override
                public void run()
                {
                    try
                    {
                        RetrofitNetwork rn = new RetrofitNetwork();
                        RequestCallback<List<Team>> rc = new RequestCallback<List<Team>>() {

                            @Override
                            public void onSuccess(List<Team> response) {
                                Team teamBuilder;
                                for(Team t : response){
                                    try {
                                        om.getTeamDao().create(t);
                                    } catch (DBException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }

                            @Override
                            public void onFailed(NetworkException e) {
                                System.out.println("Error");
                            }
                        };
                        rn.getTeams(rc);

                    }
                    catch (Exception e )
                    {
                        e.printStackTrace();
                    }
                }
            } );
            System.out.println(om.getTeamDao().getAll().size());
        } catch (DBException e) {
            e.printStackTrace();
        }
    }
}
