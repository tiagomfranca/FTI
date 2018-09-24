package tiago.exercicios.ex0919;

public enum MesesEnum {
	JANEIRO(1), FEVEREIRO(2), MARCO(3), ABRIL(4), MAIO(5), JUNHO(6), JULHO(7), AGOSTO(8), 
	SETEMBRO(9), OUTUBRO(10), NOVEMBRO(11), DEZEMBRO(12);

	int numeroDoMes;
	private static MesesEnum instance;
	
	MesesEnum(int numeroDoMes){
		this.numeroDoMes = numeroDoMes;
	}
	
	public int getNumeroDoMes(){
		return numeroDoMes;
	}
	
	public static boolean isJaneiro(MesesEnum mes){
		return mes.equals(MesesEnum.JANEIRO);
	}
	
	public static boolean isFevereiro(MesesEnum mes){
		return mes.equals(MesesEnum.FEVEREIRO);
	}
	
	public static boolean isMarco(MesesEnum mes){
		return mes.equals(MesesEnum.MARCO);
	}
	
	public static boolean isAbril(MesesEnum mes){
		return mes.equals(MesesEnum.ABRIL);
	}
	
	public static boolean isMaio(MesesEnum mes){
		return mes.equals(MesesEnum.MAIO);
	}
	
	public static boolean isJunho(MesesEnum mes){
		return mes.equals(MesesEnum.JUNHO);
	}
	
	public static boolean isJulho(MesesEnum mes){
		return mes.equals(MesesEnum.JULHO);
	}
	
	public static boolean isAgosto(MesesEnum mes){
		return mes.equals(MesesEnum.AGOSTO);
	}
	
	public static boolean isSetembro(MesesEnum mes){
		return mes.equals(MesesEnum.SETEMBRO);
	}
	
	public static boolean isOutubro(MesesEnum mes){
		return mes.equals(MesesEnum.OUTUBRO);
	}
	
	public static boolean isNovembro(MesesEnum mes){
		return mes.equals(MesesEnum.NOVEMBRO);
	}
	
	public static boolean isDezembro(MesesEnum mes){
		return mes.equals(MesesEnum.DEZEMBRO);
	}
	
	public static MesesEnum getInstance(int numeroDoMes) {
		switch (numeroDoMes){
			case 1: instance = JANEIRO; break;
			case 2: instance = FEVEREIRO; break;
			case 3: instance = MARCO; break;
			case 4: instance = ABRIL; break;
			case 5: instance = MAIO; break;
			case 6: instance = JUNHO; break;
			case 7: instance = JULHO; break;
			case 8: instance = AGOSTO; break;
			case 9: instance = SETEMBRO; break;
			case 10: instance = OUTUBRO; break;
			case 11: instance = NOVEMBRO; break;
			case 12: instance = DEZEMBRO; break;
		}
	    return instance;
		}
}