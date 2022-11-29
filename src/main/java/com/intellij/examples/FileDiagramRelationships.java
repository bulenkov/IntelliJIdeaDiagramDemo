/*
 * Copyright 1998-2014 Konstantin Bulenkov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
