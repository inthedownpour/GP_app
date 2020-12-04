package sdk.chat.gp_app.listener;

import sdk.chat.gp_app.view.PostInfo;

public interface OnPostListener {
    void onDelete(PostInfo postInfo);
    void onModify();
}
