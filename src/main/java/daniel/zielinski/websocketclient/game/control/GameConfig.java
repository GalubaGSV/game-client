package daniel.zielinski.websocketclient.game.control;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import daniel.zielinski.websocketclient.game.config.GameEntityFactory;
import daniel.zielinski.websocketclient.websocket.WebsocketMessageSender;
import daniel.zielinski.websocketclient.websocket.model.output.*;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.almasb.fxgl.dsl.FXGL.getGameWorld;
import static daniel.zielinski.websocketclient.websocket.model.output.WebSocketOutputCommandType.PLAYER_MOVE;

@Service
@RequiredArgsConstructor
public class GameConfig {

    private final WebsocketMessageSender websocketMessageSender;

    private final GameEntityFactory gameEntityFactory;

    private final LoginFormConfig loginFormConfig;

    public void initControllerSettings() {
        FXGL.onKey(KeyCode.W, () -> {
            WebSocketOutputCommandPlayerMove playerMove = WebSocketOutputCommandPlayerMove.builder()
                    .actionName(PLAYER_MOVE.name()).data(OutputCommandPlayerMove.builder()
                            .direction("Y")
                            .distance(-5)
                            .build())
                    .build();
            websocketMessageSender.send(playerMove);
        });

        FXGL.onKey(KeyCode.S, () -> {
            WebSocketOutputCommandPlayerMove playerMove = WebSocketOutputCommandPlayerMove.builder()
                    .actionName(PLAYER_MOVE.name()).data(OutputCommandPlayerMove.builder()
                            .direction("Y")
                            .distance(5)
                            .build())
                    .build();
            websocketMessageSender.send(playerMove);
        });

        FXGL.onKey(KeyCode.A, () -> {
            WebSocketOutputCommandPlayerMove playerMove = WebSocketOutputCommandPlayerMove.builder()
                    .actionName(PLAYER_MOVE.name()).data(OutputCommandPlayerMove.builder()
                            .direction("X")
                            .distance(-5)
                            .build())
                    .build();
            websocketMessageSender.send(playerMove);
        });

        FXGL.onKey(KeyCode.D, () -> {
            WebSocketOutputCommandPlayerMove playerMove = WebSocketOutputCommandPlayerMove.builder()
                    .actionName(PLAYER_MOVE.name()).data(OutputCommandPlayerMove.builder()
                            .direction("X")
                            .distance(5)
                            .build())
                    .build();
            websocketMessageSender.send(playerMove);
        });
    }

    public void game() {
        getGameWorld().addEntityFactory(gameEntityFactory);

        Button button = loginFormConfig.initLoginButton();

        FXGL.entityBuilder()
                .at(50, 50)
                .view(button)
                .buildAndAttach();
    }

}
