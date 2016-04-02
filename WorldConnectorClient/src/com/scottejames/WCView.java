package com.scottejames;
import net.miginfocom.swt.MigLayout;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import scott.mvc.Model;
import scott.mvc.View;
import scott.mvc.ViewEvent;
import scott.mvc.gui.Utils;
import scott.mvc.gui.table.FTableController;
import scott.mvc.gui.table.FTableView;

import com.scottejames.WCController.WC_EVENT;

public class WCView extends View
{

	private WCModel _model = null;
	private Text _comment;
	private Button _submit;
	private Button _refresh;

	public WCView(Shell parentShell, WCModel model)
	{
		super(parentShell, model);
		_model = model;

		getShell().setSize(500, 10000);
		MigLayout layout = new MigLayout("");
		layout.setColumnConstraints("[][][]");
		layout.setRowConstraints("[][][]");
		getShell().setLayout(layout);

		Shell composite = getShell();

		FTableView table = new FTableView(this, _model.getTableModel(),
				"grow,hmin 100,wmin 200,span,wrap");

		FTableController tableCont = new FTableController(
				_model.getTableModel(), table);
		table.open(tableCont);

		Utils.createLabel(composite, "Enter Comment", "");
		_comment = Utils.createTextField(composite, "", "width 300,grow");
		_submit = Utils.createButton(composite, "submit", "right");
		_refresh = Utils.createButton(composite, "refresh", "right");

		addController();
	}



	private void addController()
	{
		_submit.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				String text = _comment.getText();
				_comment.setText("");
				_submit.setFocus();
				sendEvent(new ViewEvent(WC_EVENT.SUBMIT_COMMENT, text));
			}
		});
		_refresh.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				String text = _comment.getText();
				_comment.setText("");
				_submit.setFocus();
				sendEvent(new ViewEvent(WC_EVENT.REFRESH_COMMENTS, text));
			}
		});
	}
	
    @Override
    public void modelUpdated(Model m)
    {
    	WCModel model = (WCModel) getModel();
    	WCTableModel tableModel = (WCTableModel) model.getTableModel();
    	System.out.println("Model updated");
    }

}
