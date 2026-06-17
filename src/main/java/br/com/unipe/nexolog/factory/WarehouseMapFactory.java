package br.com.unipe.nexolog.factory;

import br.com.unipe.nexolog.graph.Graph;
import br.com.unipe.nexolog.model.WarehouseMap;

public final class WarehouseMapFactory {
    private WarehouseMapFactory() {
        // Classe utilitária
    }

    public static WarehouseMap createSmallWarehouse() {
        Graph graph = new Graph(false, true);

        graph.addVertex("RECEBIMENTO", 0, 0);
        graph.addVertex("ARMAZENAMENTO_A", 2, 1);
        graph.addVertex("ARMAZENAMENTO_B", 2, -1);
        graph.addVertex("SEPARACAO", 5, 0);
        graph.addVertex("EXPEDICAO", 8, 0);

        graph.addEdge("R-A", "RECEBIMENTO", "ARMAZENAMENTO_A", 2);
        graph.addEdge("R-B", "RECEBIMENTO", "ARMAZENAMENTO_B", 4);

        graph.addEdge("A-S", "ARMAZENAMENTO_A", "SEPARACAO", 3);
        graph.addEdge("B-S", "ARMAZENAMENTO_B", "SEPARACAO", 1);

        graph.addEdge("S-E", "SEPARACAO", "EXPEDICAO", 2);

        return new WarehouseMap(
                "Centro de Distribuição Pequeno",
                graph
        );
    }

    public static WarehouseMap createMediumWarehouse() {
        Graph graph = new Graph(false, true);

        graph.addVertex("RECEBIMENTO", 0, 0);
        graph.addVertex("CORREDOR_A", 2, 2);
        graph.addVertex("CORREDOR_B", 2, -2);
        graph.addVertex("CORREDOR_C", 4, 0);
        graph.addVertex("ARMAZENAMENTO_A", 4, 4);
        graph.addVertex("ARMAZENAMENTO_B", 4, -4);
        graph.addVertex("EMBALAGEM", 7, 0);
        graph.addVertex("EXPEDICAO", 10, 0);

        graph.addEdge("R-A", "RECEBIMENTO", "CORREDOR_A", 2);
        graph.addEdge("R-B", "RECEBIMENTO", "CORREDOR_B", 5);

        graph.addEdge("A-AA", "CORREDOR_A", "ARMAZENAMENTO_A", 3);
        graph.addEdge("A-C", "CORREDOR_A", "CORREDOR_C", 2);

        graph.addEdge("B-AB", "CORREDOR_B", "ARMAZENAMENTO_B", 2);
        graph.addEdge("B-C", "CORREDOR_B", "CORREDOR_C", 1);

        graph.addEdge("AA-EMB", "ARMAZENAMENTO_A", "EMBALAGEM", 4);
        graph.addEdge("AB-EMB", "ARMAZENAMENTO_B", "EMBALAGEM", 2);

        graph.addEdge("C-EMB", "CORREDOR_C", "EMBALAGEM", 3);

        graph.addEdge("EMB-E", "EMBALAGEM", "EXPEDICAO", 2);

        return new WarehouseMap(
                "Centro de Distribuição Médio",
                graph
        );
    }

    public static WarehouseMap createLargeWarehouse() {

        Graph graph = new Graph(false, true);

        graph.addVertex("RECEBIMENTO", 0, 0);

        graph.addVertex("CORREDOR_A", 2, 3);
        graph.addVertex("CORREDOR_B", 2, -3);
        graph.addVertex("CORREDOR_C", 4, 3);
        graph.addVertex("CORREDOR_D", 4, -3);

        graph.addVertex("ARMAZENAMENTO_A", 4, 6);
        graph.addVertex("ARMAZENAMENTO_B", 4, -6);
        graph.addVertex("ARMAZENAMENTO_C", 6, 6);

        graph.addVertex("SEPARACAO_A", 8, 3);
        graph.addVertex("SEPARACAO_B", 8, -3);

        graph.addVertex("EMBALAGEM", 11, 0);
        graph.addVertex("EXPEDICAO", 14, 0);

        graph.addEdge("R-A", "RECEBIMENTO", "CORREDOR_A", 2);
        graph.addEdge("R-B", "RECEBIMENTO", "CORREDOR_B", 5);

        graph.addEdge("A-AA", "CORREDOR_A", "ARMAZENAMENTO_A", 2);
        graph.addEdge("A-C", "CORREDOR_A", "CORREDOR_C", 3);

        graph.addEdge("B-AB", "CORREDOR_B", "ARMAZENAMENTO_B", 2);
        graph.addEdge("B-D", "CORREDOR_B", "CORREDOR_D", 4);

        graph.addEdge("C-AC", "CORREDOR_C", "ARMAZENAMENTO_C", 2);
        graph.addEdge("C-SA", "CORREDOR_C", "SEPARACAO_A", 5);

        graph.addEdge("D-SB", "CORREDOR_D", "SEPARACAO_B", 3);

        graph.addEdge("AA-SA", "ARMAZENAMENTO_A", "SEPARACAO_A", 3);
        graph.addEdge("AB-SB", "ARMAZENAMENTO_B", "SEPARACAO_B", 2);
        graph.addEdge("AC-SA", "ARMAZENAMENTO_C", "SEPARACAO_A", 1);

        graph.addEdge("SA-EMB", "SEPARACAO_A", "EMBALAGEM", 2);
        graph.addEdge("SB-EMB", "SEPARACAO_B", "EMBALAGEM", 4);

        graph.addEdge("EMB-E", "EMBALAGEM", "EXPEDICAO", 2);

        return new WarehouseMap(
                "Centro de Distribuição Grande",
                graph
        );
    }

    // --- NOVOS MAPAS GIGANTES ADICIONADOS ABAIXO ---

    public static WarehouseMap createMegaWarehouse() {
        Graph graph = new Graph(false, true);

        // Zonas de Recebimento e Triagem
        graph.addVertex("DOCA_NORTE", 0, 10);
        graph.addVertex("DOCA_SUL", 0, -10);
        graph.addVertex("TRIAGEM", 4, 0);

        // Corredores Principais (Eixo X)
        graph.addVertex("CORREDOR_N1", 6, 8);
        graph.addVertex("CORREDOR_N2", 12, 8);
        graph.addVertex("CORREDOR_C1", 6, 0); // Central
        graph.addVertex("CORREDOR_C2", 12, 0);
        graph.addVertex("CORREDOR_S1", 6, -8);
        graph.addVertex("CORREDOR_S2", 12, -8);

        // Zonas de Armazenamento
        graph.addVertex("ZONA_FRIO_A", 9, 12);
        graph.addVertex("ZONA_FRIO_B", 15, 12);
        graph.addVertex("ZONA_SECA_A", 9, 4);
        graph.addVertex("ZONA_SECA_B", 15, 4);
        graph.addVertex("ZONA_PESADA_A", 9, -4);
        graph.addVertex("ZONA_PESADA_B", 15, -4);
        graph.addVertex("ZONA_QUIMICA", 9, -12);

        // Separação e Qualidade
        graph.addVertex("SEPARACAO_N", 18, 6);
        graph.addVertex("SEPARACAO_S", 18, -6);
        graph.addVertex("CONTROLE_QUALIDADE", 22, 0);

        // Embalagem e Expedição
        graph.addVertex("EMBALAGEM_A", 25, 4);
        graph.addVertex("EMBALAGEM_B", 25, -4);
        graph.addVertex("EXPEDICAO_PRINCIPAL", 30, 0);
        graph.addVertex("EXPEDICAO_EXPRESSA", 30, 8);

        // --- CONEXÕES (ARESTAS) ---
        graph.addEdge("DN-TR", "DOCA_NORTE", "TRIAGEM", 6);
        graph.addEdge("DS-TR", "DOCA_SUL", "TRIAGEM", 6);
        graph.addEdge("DN-CN1", "DOCA_NORTE", "CORREDOR_N1", 4);

        graph.addEdge("TR-CN1", "TRIAGEM", "CORREDOR_N1", 5);
        graph.addEdge("TR-CC1", "TRIAGEM", "CORREDOR_C1", 2);
        graph.addEdge("TR-CS1", "TRIAGEM", "CORREDOR_S1", 5);

        graph.addEdge("CN1-CN2", "CORREDOR_N1", "CORREDOR_N2", 6);
        graph.addEdge("CN1-ZFA", "CORREDOR_N1", "ZONA_FRIO_A", 3);
        graph.addEdge("CN2-ZFB", "CORREDOR_N2", "ZONA_FRIO_B", 3);
        graph.addEdge("CN1-ZSA", "CORREDOR_N1", "ZONA_SECA_A", 3);

        graph.addEdge("CC1-CC2", "CORREDOR_C1", "CORREDOR_C2", 6);
        graph.addEdge("CC1-CN1", "CORREDOR_C1", "CORREDOR_N1", 8);
        graph.addEdge("CC1-CS1", "CORREDOR_C1", "CORREDOR_S1", 8);
        graph.addEdge("CC2-CN2", "CORREDOR_C2", "CORREDOR_N2", 8);
        graph.addEdge("CC2-ZSB", "CORREDOR_C2", "ZONA_SECA_B", 3);
        graph.addEdge("CC2-ZPA", "CORREDOR_C2", "ZONA_PESADA_A", 3);

        graph.addEdge("CS1-CS2", "CORREDOR_S1", "CORREDOR_S2", 6);
        graph.addEdge("CS1-ZQ", "CORREDOR_S1", "ZONA_QUIMICA", 3);
        graph.addEdge("CS2-ZPB", "CORREDOR_S2", "ZONA_PESADA_B", 3);

        graph.addEdge("ZFB-SEPN", "ZONA_FRIO_B", "SEPARACAO_N", 4);
        graph.addEdge("ZSB-SEPN", "ZONA_SECA_B", "SEPARACAO_N", 4);
        graph.addEdge("ZPB-SEPS", "ZONA_PESADA_B", "SEPARACAO_S", 4);
        graph.addEdge("ZQ-SEPS", "ZONA_QUIMICA", "SEPARACAO_S", 5);

        graph.addEdge("SEPN-CQ", "SEPARACAO_N", "CONTROLE_QUALIDADE", 5);
        graph.addEdge("SEPS-CQ", "SEPARACAO_S", "CONTROLE_QUALIDADE", 5);
        graph.addEdge("SEPN-EMBA", "SEPARACAO_N", "EMBALAGEM_A", 7);

        graph.addEdge("CQ-EMBA", "CONTROLE_QUALIDADE", "EMBALAGEM_A", 3);
        graph.addEdge("CQ-EMBB", "CONTROLE_QUALIDADE", "EMBALAGEM_B", 3);

        graph.addEdge("EMBA-EXP", "EMBALAGEM_A", "EXPEDICAO_PRINCIPAL", 5);
        graph.addEdge("EMBB-EXP", "EMBALAGEM_B", "EXPEDICAO_PRINCIPAL", 5);
        graph.addEdge("EMBA-EXEX", "EMBALAGEM_A", "EXPEDICAO_EXPRESSA", 4);

        return new WarehouseMap(
                "Centro de Distribuição Mega (Múltiplos Setores)",
                graph
        );
    }

    public static WarehouseMap createUltraGridWarehouse() {
        Graph graph = new Graph(false, true);

        graph.addVertex("RECEBIMENTO", 0, 0);
        graph.addVertex("EXPEDICAO", 30, 0);

        String[][] grade = new String[3][4];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                String nome = "CR_" + i + "_" + j;
                grade[i][j] = nome;
                int x = 5 + (j * 7);
                int y = 5 - (i * 5);
                graph.addVertex(nome, x, y);
            }
        }

        graph.addEdge("REC-00", "RECEBIMENTO", grade[0][0], 6);
        graph.addEdge("REC-10", "RECEBIMENTO", grade[1][0], 5);
        graph.addEdge("REC-20", "RECEBIMENTO", grade[2][0], 6);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                if (j < 3) {
                    int pesoHorizontal = (i == 1) ? 2 : 4;
                    graph.addEdge("H_"+i+"_"+j, grade[i][j], grade[i][j+1], pesoHorizontal);
                }
                if (i < 2) {
                    graph.addEdge("V_"+i+"_"+j, grade[i][j], grade[i+1][j], 3);
                }
            }
        }

        graph.addVertex("PRATELEIRA_A", 8, 2);
        graph.addVertex("PRATELEIRA_B", 15, -2);
        graph.addVertex("PRATELEIRA_C", 22, 2);
        graph.addVertex("PRATELEIRA_D", 15, 8);

        graph.addEdge("PRA-1", "PRATELEIRA_A", grade[0][0], 2);
        graph.addEdge("PRA-2", "PRATELEIRA_A", grade[1][1], 2);

        graph.addEdge("PRB-1", "PRATELEIRA_B", grade[1][1], 2);
        graph.addEdge("PRB-2", "PRATELEIRA_B", grade[2][2], 2);

        graph.addEdge("PRC-1", "PRATELEIRA_C", grade[0][2], 2);
        graph.addEdge("PRC-2", "PRATELEIRA_C", grade[1][3], 2);

        graph.addEdge("PRD-1", "PRATELEIRA_D", grade[0][1], 2);
        graph.addEdge("PRD-2", "PRATELEIRA_D", grade[0][2], 2);

        graph.addEdge("03-EXP", grade[0][3], "EXPEDICAO", 6);
        graph.addEdge("13-EXP", grade[1][3], "EXPEDICAO", 5);
        graph.addEdge("23-EXP", grade[2][3], "EXPEDICAO", 6);

        return new WarehouseMap(
                "Armazém Ultra Grid (Alta Densidade)",
                graph
        );
    }
}