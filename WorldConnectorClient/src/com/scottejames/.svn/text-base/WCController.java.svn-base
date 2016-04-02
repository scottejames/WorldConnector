package com.scottejames;

import java.io.IOException;
import java.util.List;

import scott.mvc.BaseView;
import scott.mvc.Controller;
import scott.mvc.Model;
import scott.mvc.ViewEvent;

import com.scottejames.connection.IServerInterface;
import com.scottejames.connection.ServerInterfaceBuilder;
import com.shineyappythings.restfulservice.Message;

public class WCController extends Controller {

	private IServerInterface _serverInterface = ServerInterfaceBuilder
			.getInstance().build();

	public enum WC_EVENT {
		WORLD_CONNECTOR_EVENT, SUBMIT_COMMENT, COMMENTS_UPDATED, REFRESH_COMMENTS
	};

	public WCController(Model m, BaseView v) {
		super(m, v);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void handleEvent(ViewEvent event) {
		if (!(event.event instanceof WC_EVENT)) {
			System.err
					.println("[handleEvent]  unable to handle event so passing up");
			super.handleEvent(event);
		} else {
			switch ((WC_EVENT) event.event) {

			case SUBMIT_COMMENT:
				submitComment((String) event.arg);
				break;
			case REFRESH_COMMENTS:
				refreshComments((String) event.arg);
				break;
			}
		}

	}

	private void refreshComments(String arg) {
		try {
			WCModel model = (WCModel) getModel();
			List<Message> comments = _serverInterface.getNewMessages();
			model.addComments(comments);
			model.notifyUpdate();
			if (comments != null)
				for (Message m : comments) {
					System.out.println("Loaded message: " + m);
				}
		} catch (IOException e) {
			// *SJ* better error handling would be nice.
			e.printStackTrace();
			System.exit(-1);
		}

	}

	private void loadComments() {

	}

	private void submitComment(String comment) {
		System.out.println("Submit comment " + comment);

		Message message = new Message(comment, "sjames1");

		try {
			if (_serverInterface.postMessage(message))
				System.out.println("POST got sent");
			else
				System.err.println("POST got sent but failed!");

		} catch (IOException e) {
			// *SJ* better error handling would be nice.
			e.printStackTrace();
			System.exit(-1);
		}

	}
}
