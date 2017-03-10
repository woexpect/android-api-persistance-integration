package co.edu.pdam.eci.persistenceapiintegration.network;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Created by estudiante on 3/9/17.
 */

public interface RequestCallback<T> {

    void onSuccess( T response );

    void onFailed( NetworkException e );
}
