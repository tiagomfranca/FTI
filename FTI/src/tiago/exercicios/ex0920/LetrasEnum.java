package tiago.exercicios.ex0920;

public enum LetrasEnum {
	A(1), B(2), C(3), D(4), E(5), F(6), G(7), H(8), I(9), J(10), K(11), L(12), M(13), N(14),
	O(15), P(16), Q(17), R(18), S(19), T(20), U(21), V(22), W(23), X(24), Y(25), Z(26);
	
	private int numeroLetra;
	private static LetrasEnum instance;
	
	LetrasEnum(int numeroLetra){
		this.numeroLetra = numeroLetra;
	}
	
	public static LetrasEnum getInstance(int numeroLetra) {
		switch (numeroLetra){
			case 1: instance = A; break;
			case 2: instance = B; break;
			case 3: instance = C; break;
			case 4: instance = D; break;
			case 5: instance = E; break;
			case 6: instance = F; break;
			case 7: instance = G; break;
			case 8: instance = H; break;
			case 9: instance = I; break;
			case 10: instance = J; break;
			case 11: instance = K; break;
			case 12: instance = L; break;
			case 13: instance = M; break;
			case 14: instance = N; break;
			case 15: instance = O; break;
			case 16: instance = P; break;
			case 17: instance = Q; break;
			case 18: instance = R; break;
			case 19: instance = S; break;
			case 20: instance = T; break;
			case 21: instance = U; break;
			case 22: instance = V; break;
			case 23: instance = W; break;
			case 24: instance = X; break;
			case 25: instance = Y; break;
			case 26: instance = Z; break;
		}
	    return instance;
		}
}