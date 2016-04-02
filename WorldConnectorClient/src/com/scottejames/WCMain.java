package com.scottejames;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;


public class WCMain
{
    public  static Shell shell = null;
    public static Display display = null;
    
	public static void main(String[] args)
	{
		 display = new Display();
		 shell = new Shell(display);

		WCModel model = new WCModel();
		WCView view = new WCView(shell, model);
		WCController controller = new WCController(model, view);
		view.open(controller);
	}
}
