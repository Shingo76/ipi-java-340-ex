package com.ipiecoles.java.java340.model;

import java.util.Arrays;
import java.util.Collection;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

//Test paramétré------------------------------------------------------------------------------

@RunWith(value=Parameterized.class)
public class ManagerTest {
	
		@Parameter(value=0)
		public Double salaireAvantAugmentation;

		@Parameter(value=1)
		public Double pourcentageAugmentation;
		
		@Parameter(value=2)
		public Double salaireApresAugmentation;
		
		public static Double salaireRand = Math.ceil(Math.random()*2000*Entreprise.INDICE_MANAGER*100)/100;
		public static Double pourcentageRand = Math.ceil(Math.random()*100)/100;
		
		@Parameters(name="le salaire {0} est augmenté de {1} pour devenir {2}")
		public static Collection<Object[]> data(){
			
			return Arrays.asList(new Object[][]{
					
					{1000d, 0.5d, 1500d},
					{salaireRand, pourcentageRand, salaireRand*(1+pourcentageRand)},
					{salaireRand, null, salaireRand}
					
			});
		}
		
		@Test
		public void testAugmenterSalaire() {
			
			//Given
			Manager manager = new Manager();
			
			manager.setSalaire(salaireAvantAugmentation);
			
			if(pourcentageAugmentation==null) 
				{
					pourcentageAugmentation=0d;
				}
			
			manager.augmenterSalaire(pourcentageAugmentation);
			
			//When
			salaireApresAugmentation = manager.getSalaire();
			
			//Then
			Assertions.assertThat(salaireApresAugmentation).isEqualTo(salaireAvantAugmentation*Entreprise.INDICE_MANAGER*(1+pourcentageAugmentation));
		}
		
		//Tests unitaires-------------------------------------------------------------------------------
		@Test
		public void testUnitAugmenterSalaire() {
					
			//Given
			Manager manager = new Manager();
			
			Double salaireBase = 1000d;
			manager.setSalaire(salaireBase);
			Double pc = 0.5d;
			manager.augmenterSalaire(pc);
			
			//When
			Double salaire = manager.getSalaire();
			
			//Then
			Assertions.assertThat(salaire).isEqualTo(salaireBase*Entreprise.INDICE_MANAGER*(1+pc));
		}	
}
