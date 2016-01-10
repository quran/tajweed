package com.quran.tajweed.model;

import com.quran.tajweed.rule.GhunnaRule;
import com.quran.tajweed.rule.IdghamRule;
import com.quran.tajweed.rule.IkhfaRule;
import com.quran.tajweed.rule.IqlabRule;
import com.quran.tajweed.rule.MeemRule;
import com.quran.tajweed.rule.QalqalahRule;
import com.quran.tajweed.rule.Rule;

import java.util.Arrays;
import java.util.List;

public class TajweedRule {

  public static List<TajweedRule> RULES = Arrays.asList(
      new TajweedRule(new GhunnaRule()),
      new TajweedRule(new IdghamRule()),
      new TajweedRule(new IkhfaRule()),
      new TajweedRule(new IqlabRule()),
      new TajweedRule(new MeemRule()),
      new TajweedRule(new QalqalahRule())
  );

  public final Rule rule;

  private TajweedRule(Rule rule) {
    this.rule = rule;
  }
}
