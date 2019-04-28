package scene;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class UiButton extends Button
{
	public UiButton(String text)
	{
		setText(text);
		setBorder(new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, new CornerRadii(20), BorderWidths.DEFAULT)));
		
//		setPadding(new Insets(0, 0, 10, 0));
		setFont(new Font(30));
		setEvent();
		unhighlight();
	}

	private void setEvent()
	{
		setOnMouseEntered
		(
			new EventHandler<Event>()
			{
				public void handle(Event event)
				{
					highlight();
				}
			}
		);
		setOnMouseExited
		(
			new EventHandler<Event>()
			{
				public void handle(Event event)
				{
					unhighlight();
				}
			}
		);
	}

	public void highlight()
	{

		this.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(20), Insets.EMPTY)));

	}

	public void unhighlight()
	{

		this.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(20), Insets.EMPTY)));

	}
}
