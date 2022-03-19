package connection;

import java.io.IOException;

public interface ISimpleHttpClient {
        String doHttpGet(String str) throws IOException;
}
