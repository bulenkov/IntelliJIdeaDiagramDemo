package com.intellij.examples;

import com.intellij.diagram.DiagramRelationshipInfo;
import com.intellij.diagram.DiagramRelationshipInfoAdapter;
import com.intellij.diagram.presentation.DiagramLineType;

import java.awt.*;

/**
 * @author Konstantin Bulenkov
 */
public interface FileDiagramRelationships {
  DiagramRelationshipInfo STRONG = new DiagramRelationshipInfoAdapter("STRONG") {
    @Override
    public Shape getStartArrow() {
      return STANDARD;
    }
  };
  DiagramRelationshipInfo SOFT = new DiagramRelationshipInfoAdapter("SOFT") {
    @Override
    public Shape getStartArrow() {
      return STANDARD;
    }

    @Override
    public DiagramLineType getLineType() {
      return DiagramLineType.DASHED;
    }
  };
}
