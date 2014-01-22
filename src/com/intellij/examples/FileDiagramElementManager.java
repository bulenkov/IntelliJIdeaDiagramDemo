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

import com.intellij.diagram.AbstractDiagramElementManager;
import com.intellij.diagram.presentation.DiagramState;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.ui.SimpleColoredText;
import org.jetbrains.annotations.Nullable;

/**
 * @author Konstantin Bulenkov
 */
public class FileDiagramElementManager extends AbstractDiagramElementManager<VirtualFile> {
  @Nullable
  @Override
  public VirtualFile findInDataContext(DataContext context) {
    return CommonDataKeys.VIRTUAL_FILE.getData(context);
  }

  @Override
  public boolean isAcceptableAsNode(Object o) {
    return o instanceof VirtualFile;
  }

  @Nullable
  @Override
  public String getElementTitle(VirtualFile file) {
    return file.getName();
  }

  @Nullable
  @Override
  public SimpleColoredText getItemName(Object o, DiagramState state) {
    return null;
  }

  @Override
  public String getNodeTooltip(VirtualFile file) {
    return null;
  }
}
