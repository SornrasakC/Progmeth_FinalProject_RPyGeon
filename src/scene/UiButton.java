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

public class UiButton extends Button
{
	public UiButton(String text)
	{
		setBorder(new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		setText(text);
		setEvent();
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

		this.setBackground(new Background(new BackgroundFill(Color.AQUAMARINE, CornerRadii.EMPTY, Insets.EMPTY)));
	}

	public void unhighlight()
	{

		this.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
	}
}
