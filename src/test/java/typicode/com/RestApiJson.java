package typicode.com;

public class RestApiJson {

    public static void main(String[] args) {
        String id = "id";
        String iduser = "userId";
        int resultiduser = Max.getMax(iduser);
        int resultid = Max.getMaxIdForUser(id,resultiduser);
        String payload="{\"postId\":\""+ iduser + "\",\"email\":\"easda@asd.pl\",\"body\":\"sdlkfjsd f  lkasjdfnlkjasdf aksdjfnlkjnd fslijasdnf  lksajdfn\"}";
        String requestUrl="https://jsonplaceholder.typicode.com/comments";
        Max.sendPostRequest(requestUrl, payload);
        System.out.println("max userid: " + resultiduser + "  max ID postów dla tego usera :" + resultid);
    }

    }

