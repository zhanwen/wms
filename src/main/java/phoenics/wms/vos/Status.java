package phoenics.wms.vos;

import java.io.Serializable;

public class Status  implements Serializable {
	/**
	 * @Description: 
	 */
	private static final long serialVersionUID = 3262815132423941857L;
	private boolean lonin=false;

	public boolean isLonin() {
		return lonin;
	}

	public void setLonin(boolean lonin) {
		this.lonin = lonin;
	}

}
