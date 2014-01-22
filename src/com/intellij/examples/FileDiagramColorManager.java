package com.intellij.examples;

import com.intellij.diagram.DiagramColorManagerBase;
import com.intellij.diagram.DiagramEdge;
import com.intellij.ui.JBColor;

import java.awt.*;

/**
 * @author Konstantin Bulenkov
 */
public class FileDiagramColorManager extends DiagramColorManagerBase {
  @Override
  public Color getEdgeColor(DiagramEdge edge) {
    final String edgeType = edge.getRelationship().toString();
    if ("SOFT".equals(edgeType)) {
      return new JBColor(new Color(9, 128, 0), new Color(83, 128, 103));
    }
    if ("STRONG".equals(edgeType)) {
      return new JBColor(new Color(0, 26, 128), new Color(140, 177, 197));
    }
    return super.getEdgeColor(edge);
  }
}
