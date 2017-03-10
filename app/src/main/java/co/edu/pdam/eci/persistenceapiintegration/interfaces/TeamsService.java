package co.edu.pdam.eci.persistenceapiintegration.interfaces;

import java.util.List;

import co.edu.pdam.eci.persistenceapiintegration.data.entity.Team;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by estudiante on 3/9/17.
 */

public interface TeamsService {

    @GET( "teams.json" )
    Call<List<Team>> getTeamsList( );

}
