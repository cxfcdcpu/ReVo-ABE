package test;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import it.unisa.dia.gas.jpbc.*;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;
//import java.lang.Math.*;
import tree_type.*;
import revoabe.*;

public class TestJPBC {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Pairing pairing = PairingFactory.getPairing("./src/a.properties");
		PairingFactory.getInstance().setUsePBCWhenPossible(true);

		ReVo_ABE testABE = new ReVo_ABE(pairing, 8);
		List<String> attr_list= new ArrayList<String>();
		attr_list.add("phd");
		attr_list.add("master");
		int user_id = 3;
		PrivateKey prik = testABE.keygen(attr_list, user_id);
		System.out.println(prik);
		prik.printPrivateKey();

		MasterKey mk = testABE.getMasterKey();
		System.out.println(mk);
		mk.printMasterKey();
		
		PublicKey pk = testABE.getPublicKey();
		System.out.println(pk);
		pk.printPublicKey();
		
	}

}
