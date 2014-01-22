package com.intellij.examples;

import com.intellij.diagram.DiagramEdgeBase;
import com.intellij.diagram.DiagramRelationshipInfo;
import com.intellij.openapi.vfs.VirtualFile;

/**
 * @author Konstantin Bulenkov
 */
public class FileEdge extends DiagramEdgeBase<VirtualFile> {
  public FileEdge(FileNode source, FileNode target, DiagramRelationshipInfo relationship) {
    super(source, target, relationship);
  }
}
