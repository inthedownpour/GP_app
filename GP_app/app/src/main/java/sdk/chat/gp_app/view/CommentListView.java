package sdk.chat.gp_app.view;

public class CommentListView {

    private int src;
    private String name;
    private String nickname;

    public CommentListView(int src, String name, String nickname) {
        this.src = src;
        this.name = name;
        this.nickname = nickname;
    }

    public int getSrc() {
        return src;
    }

    public String getName() {
        return name;
    }

    public String getNickname() {
        return nickname;
    }
}
