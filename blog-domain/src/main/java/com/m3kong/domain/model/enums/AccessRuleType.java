package com.m3kong.domain.model.enums;

public enum AccessRuleType {
  Everyone, // Public to everyone - no need to signup or logged in
  OnlyMe, // Only me can read this page - required login
  OnlyMember, // Only account registered and logged in
  Unpublish, // Have directly link to read, not able to search
  Pending // Under construction/ Prevent to access - not ready to publish
}
